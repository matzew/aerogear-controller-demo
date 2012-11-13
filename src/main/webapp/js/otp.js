$(document).ready(function() {
	if (!$('#qrcode-div')) {
		return;
	}

	if ($('#qrcode-div')) {
		var jqxhr = $.ajax('/aerogear-controller-demo/auth/otp/secret', {
	        data:{},
	        type:'GET', 
	        success:function (data) {
                console.log(data.uri);
                $('#qrcode-div').qrcode(data.uri);
                $('#val').text("URI="+data.uri);
	        }
	    });
	}
});
