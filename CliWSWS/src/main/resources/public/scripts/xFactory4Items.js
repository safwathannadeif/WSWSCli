/**
 *  xFactory4Items.js
 */
 /* xSharedItems define initialized instance "xItemsInsc" can be shared by multiple controllers/pages specfic for pick and run
 * 
 */  
angular.module('track_wsws').factory('xSharedItems' ,  function () {
  var xItemsInsc = {
    srvcItems: 
		{"items":[], "selectedIndex":-1} ,
	inxItems:
		{ "actionId":"" } /* Can Add | Upd | Del */
	}; 
  return xItemsInsc;
  }
) ;

