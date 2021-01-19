#import "ViewController.h"
#import "FacelokCallback.h"

@interface ViewController () {
    FacelokCallback *mCallback;
    IBOutlet UIView *mPreviewView;
    FLFacelokImpl *mInterface;
}

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];

    // create our callback
    mCallback = [[FacelokCallback alloc] init];

    // setup facelok
    mInterface = [FLFacelokImpl alloc];
    [mInterface initialize];
    [mInterface setCallback: mCallback];
    [mInterface setPreviewView: mPreviewView];
    [mCallback setFacelokInterface: mInterface];

    // start facelok
    FLCameraParameters *params = [[FLCameraParameters alloc] initWithFacing: FLCameraFacingEnumFront preferredFPS: 30 previewWidth: 1024 previewHeight: 768];
    [mInterface start: params];
}


@end
