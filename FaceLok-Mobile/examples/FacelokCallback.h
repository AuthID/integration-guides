#import "FaceLok/FaceLok.h"
#import <UIKit/UIKit.h>

@interface FacelokCallback : FLFacelokCallback

@property (nonatomic, weak) FLFacelokImpl *facelokInterface;

- (void)imageProcessed:(nonnull FLFaceState *)face;
- (void)detectionCompleteWithUIImage:(nonnull UIImage *)photo
    imageInfo:(nonnull NSString *)imageInfo;
- (void)imageTooDark;
- (void)imageTooLight;
- (void)faceDetected;
- (void)faceLost;
- (void)livenessEvent:(FLLivenessEvent)event;
- (void)status:(BOOL)failure
        message:(nonnull NSString *)message;
- (void)validFaceAngle:(BOOL)valid
        angle:(int32_t)angle;
- (void)cameraReady;
- (void)faceTooSmall;
- (void)imageGlare:(BOOL)detected
              info:(nonnull FLGlareInfo *)info;
- (void)failureReason:(FLFailureReason)reason;

@end
