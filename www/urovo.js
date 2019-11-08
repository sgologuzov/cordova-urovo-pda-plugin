var exec = require('cordova/exec')

exports.doScan = function(code, success, error) {
  exec(success, error, 'UrovoPDAPlugin', 'doScan', [code]);
}

exports.onBarcodeScanned = function(success, error) {
  exec(success, error, 'UrovoPDAPlugin', 'onBarcodeScanned');
}
