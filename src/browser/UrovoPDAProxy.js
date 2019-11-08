var listener;

function doScan(success, error, opts) {
	if (listener) {
		listener(opts[0], {keepCallback: true});
	}
}

function onBarcodeScanned(success, error, opts) {
	listener = success;
}

module.exports = {
  onBarcodeScanned: onBarcodeScanned,
  doScan: doScan
}

require('cordova/exec/proxy').add('UrovoPDAPlugin', module.exports)
