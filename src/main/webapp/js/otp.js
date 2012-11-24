$( document ).ready( function() {

	$(' #qrcode ').click( function( event ) {
        event.preventDefault();

		$.ajax('auth/otp/secret', {
	        data:{},
	        type:'GET', 
	        success:function (data) {
                console.log(data.uri);
                $('#qrcode-div').qrcode(data.uri);
            }
	    });
	});
});
