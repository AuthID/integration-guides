#import "FacelokCallback.h"

@interface FacelokCallback()

@end

@implementation FacelokCallback {

}

- (id) init {
    self = [super init];
    return self;
}

- (void)imageTooDark {

}

- (void)imageTooLight {

}

- (void)faceDetected {
    NSLog(@"Face Detected!");

}

- (void)faceLost {
    NSLog(@"Face Lost!");

}

- (void)validFaceAngle:(BOOL)valid 
    angle:(int32_t)angle {
    if (valid)
        NSLog(@"%@", [NSString stringWithFormat: @"Face angle changed to VALID, angle: %d", angle]);
    else
        NSLog(@"%@", [NSString stringWithFormat: @"Face angle changed to INVALID, angle: %d", angle]);
}

- (void)livenessEvent:(FLLivenessEvent)event {
    NSLog(@"%@", [NSString stringWithFormat: @"Liveness event complete: %d", (int)event]);
}

- (void)status:(BOOL)failure
        message:(nonnull NSString *)message {

}

- (void)detectionCompleteWithUIImage:(nonnull UIImage *)photo
    imageInfo:(nonnull NSString *)imageInfo {
    NSLog(@"DETECTION COMPLETE");
    [self.facelokInterface reset];
}

- (void)cameraReady {
    NSLog(@"Camera ready");
}

- (void)faceTooSmall {

}

- (void)imageProcessed:(nonnull FLFaceState *)state {

}

- (void)imageGlare:(BOOL)detected
              info:(nonnull FLGlareInfo *)info {

}

- (void)failureReason:(FLFailureReason)reason {

}

@end
