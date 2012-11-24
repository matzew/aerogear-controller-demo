$( document ).ready( function() {

	$(' #qrcode ').click( function( event ) {
        event.preventDefault();

		$.ajax('/aerogear-controller-demo/auth/otp/secret', {
	        data:{},
	        type:'GET', 
	        success:function (data) {
                console.log(data.uri);
                $('#qrcode-div').qrcode(data.uri);
            }
	    });
	});
});
