/*global cordova,window,console*/
/**
 * An Image Picker plugin for Cordova
 * 
 * Developed by Wymsee for Sync OnSet
 */

var ImagePicker = function() {

};

/*
*	success - success callback
*	fail - error callback
*	options
*		.maximumImagesCount - max images to be selected, defaults to 15
*       .title - title of picker window
*       .imagesTitle - title of images tab
*       .videosTitle - title of videos tab
*/
ImagePicker.prototype.getPictures = function(success, fail, options) {
	if (!options) {
		options = {};
	}
	
	var params = {
		maximumImagesCount: options.maximumImagesCount || 15
	};
	options.title && (params.title = options.title);
	options.imagesTitle && (params.imagesTitle = options.imagesTitle);
	options.videosTitle && (params.videosTitle = options.videosTitle);

	return cordova.exec(success, fail, "ImagePicker", "getPictures", [params]);
};

window.imagePicker = new ImagePicker();
