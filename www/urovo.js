var exec = require('cordova/exec')

exports.onBarcodeScanned = function(barcode, success, error) {
  exec(success, error, 'UrovoPDAPlugin', 'onBarcodeScanned', [barcode]);
}
