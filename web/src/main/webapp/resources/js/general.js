/**
 * 
 */

 function ClearAuthentication(LogOffPage) 
  {
     var IsInternetExplorer = false;    

     try
     {
         var agt=navigator.userAgent.toLowerCase();
         if (agt.indexOf("msie") != -1) { IsInternetExplorer = true; }
     }
     catch(e)
     {
         IsInternetExplorer = false;    
     };

     if (IsInternetExplorer) 
     {
        // Logoff Internet Explorer
        document.execCommand("ClearAuthenticationCache");
        window.location = LogOffPage;
     }
     else 
     {
        // Logoff every other browsers
    $.ajax({
         username: 'unknown',
         password: 'WrongPassword',
             url: './cgi-bin/PrimoCgi',
         type: 'GET',
         beforeSend: function(xhr)
                 {
            xhr.setRequestHeader("Authorization", "Basic AAAAAAAAAAAAAAAAAAA=");
         },

                 error: function(err)
                 {
                    window.location = LogOffPage;
             }
    });
     }
  }


$( document ).ready(function() {
	
	
	
	

$("#btnLogout").click(function(e){
	
	 //ClearAuthentication("force_logout.html"); 
       try {
        	//e.preventDefault();                                                        
            var request = new XMLHttpRequest();   
            request.open("get", "../logout", false, "false", "false");                                                                                                                               
            request.send();
            document.execCommand("ClearAuthenticationCache");
            var out = window.location.href.replace(/:\/\//, '://log:out@');
            //alert(out);
            jQuery.get(out).error(function() {
                window.location = "../logout.xhtml";
            });         
            window.location.href('../logout.xhtml');
        } catch (exception) {}
    });

});