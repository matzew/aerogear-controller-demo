var getHost = function() {
	var port = '8080';
	var hname = window.location.hostname;
	
	if(hname.indexOf("rhcloud.com") == -1){
		return 'http://' + hname + ":" + port + "/todo";	
	} else {
		return 'http://' + hname + "/todo";	
	}
};

function storeToken(token) {
	try {
		sessionStorage.setItem("AUTH_TOKEN", token);
	} catch (e) {
		alert('Your browser does not support HTML5 sessionStorage. Try upgrading.');
	}
}

function getToken() {
	try {
		return sessionStorage.getItem("AUTH_TOKEN");
	} catch (e) {
		alert('Your browser does not support HTML5 sessionStorage. Try upgrading.');
	}
}

$.ajaxSetup({
	headers : {
		"Auth-Token" : getToken()
	},
	error : function(xhr, textStatus, errorThrown) {
		if (window.location.pathname.indexOf("login.html", 0) == -1) {
			if (window.location.pathname.indexOf("error.html", 0) == -1) {
				if (xhr.status == 500 || xhr.status == 403) {
					window.location = getHost() + "/error.html";
					return;
				}
			}

			window.location = getHost() + "/login.html";
		}
	}
});

$(document).ready(function() {
	if (!$('#login-btn')) {
		return;
	}
	
	$('#login-btn').click(function() {
		var jqxhr = $.ajax('/todo-server/auth/login', {
			contentType: "application/json",
            dataType:'json',
            data:JSON.stringify({id:$('#username').val(),password:$('#password').val()}),
            type:'POST', 
            success:function (data, textStatus, jqXHR) {
                if (data.id != null) {
					window.location = getHost() + "/index.jsp";
                    storeToken(jqXHR.getResponseHeader("Auth-Token"));
                } else {
                	$('#login-msg').text("Authentication failed. Try again ...");
                }
            },
            complete:function (data, textStatus, jqXHR) {
                alert(jqXHR.getResponseHeader("Auth-Token"));
            }

        });
		return false; // prevents submit of the form
	});
	
});

$(document).ready(function() {
	if (!$('#logout-btn')) {
		return;
	}
	
	$('#logout-btn').click(function() {
		var jqxhr = $.ajax('/todo-server/auth/logout', {
            data:{},
            type:'GET', 
            success:function (data) {
            	window.location = getHost() + "/login.html";
            }
        });
		return false; // prevents submit of the form
	});
});

$(document).ready(function() {
	if (!$('#userinfo-msg')) {
		return;
	}

	if ($('#userinfo-msg')) {
		var jqxhr = $.ajax('/todo-server/auth/userinfo', {
	        data:{},
	        type:'GET', 
	        success:function (data) { 
	        	$('#userinfo-msg').text("Welcome " + data.id + ". Your roles are: " + data.roles);
	        }
	    });
	}
});

var popup = null;

function sendMainPage() {
	popup.close();
	window.location = getHost() + "/index.jsp";
}

$(document).ready(function() {

	//Twitter signin
	$('#get-secret').click(function(e) {
        e.preventDefault();
        popup = window.open(getHost() + "/index.jsp", "name", "height=512, width=512");
        popup.focus();
        popup.window.reload = function(){
        	if(popup.document.body.innerHTML.indexOf("true") > -1){
            	popup.close();
            	var jqxhr = $.ajax('/todo-server/auth/otp/secret', {
        			contentType: "application/json",
                    dataType:'json',
                    type:'POST', 
                    success:function (data, textStatus, jqXHR) {
                        if (data.id != null) {
        					window.location = getHost() + "/index.jsp";
                            storeToken(jqXHR.getResponseHeader("Auth-Token"));
                        } else {
                        	$('#login-msg').text("Authentication failed. Try again ...");
                        }
                    }
                });
        	}
        };
        
		return false; // prevents submit of the form
	});
});

$(document).ready(function() {
	if (!$('#otp-btn')) {
		return;
	}
	
	$('#otp-btn').click(function() {
		var jqxhr = $.ajax('/todo-server/auth/otp', {
			contentType: "application/json",
            dataType:'json',
            data:JSON.stringify({id:$('#username').val(),password:$('#password').val(),otp:$('#otp').val()}),
            type:'POST', 
            success:function (data, textStatus, jqXHR) {
                if (data.id != null) {
					window.location = getHost() + "/index.jsp";
                    storeToken(jqXHR.getResponseHeader("Auth-Token"));
                } else {
                	$('#login-msg').text("Authentication failed. Try again ...");
                }
            }
        });
		return false; // prevents submit of the form
	});
	
});
