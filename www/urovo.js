var exec = require('cordova/exec')

exports.onBarcodeScanned = function(success, error) {
  exec(success, error, 'UrovoPDAPlugin', 'onBarcodeScanned');
}
