cordova-image-multiselect
===================

Cordova Plugin For Multiple Image Selection - implemented for Android 4.0.3 and above.

## Installing the plugin

The plugin conforms to the Cordova plugin specification, it can be installed
using the Cordova command line interface.

    cordova plugin add https://github.com/RattleInGlasses/cordova-image-multiselect.git


## Using the plugin

The plugin creates the object `window.imagePicker` with the method `getPictures(success, fail, options)`

Example - Get images or videos (all default options):
```javascript
window.imagePicker.getPictures(
    function(results) {
        for (var i = 0; i < results.length; i++) {
            console.log('Image URI: ' + results[i]);
        }
    }, function (error) {
        console.log('Error: ' + error);
    }
);
```

Example - Get at most 10 images or videos with custom picker title:
```javascript
window.imagePicker.getPictures(
    function(results) {
        for (var i = 0; i < results.length; i++) {
            console.log('Image URI: ' + results[i]);
        }
    }, function (error) {
        console.log('Error: ' + error);
    }, {
        maximumImagesCount: 10,
        title: 'Select images',
        imagesTitle: 'My Images',
        videosTitle: 'My Videos'
    }
);
```

### Options

    options = {
        // max images to be selected, defaults to 15
        maximumImagesCount: int,
        // title of picker window, defaults to "Select file"
        title: string,
        // title of images tab, defaults to "Images"
        imagesTitle: string,
        // title of videos tab, defaults to "Videos"
        videosTitle: string
    };
    
   
## Libraries used

#### Multiple Media Picker

For Android this plugin uses Multiple Media Picker, with modifications. Multiple Media Picker uses the Apache license which can be found in the file APACHE_LICENSE.

https://github.com/erikagtierrez/multiple-media-picker

## License

The MIT License

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
