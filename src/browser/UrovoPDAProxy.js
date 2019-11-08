var listener;

function doScan(success, error, opts) {
	return listener(opts[0]);
}

function onBarcodeScanned(success, error, opts) {
	listener = success;
	return success();
}

module.exports = {
  onBarcodeScanned: onBarcodeScanned,
  doScan: doScan
}

require('cordova/exec/proxy').add('UrovoPDAPlugin', module.exports)
