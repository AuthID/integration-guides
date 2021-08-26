![Ipsidy](../../images/authid.png)
# FaceLok Mobile - Project Setup

<!-- TOC -->
- [FaceLok Mobile - Project Setup](#facelok-mobile---project-setup)
  - [Android](#android)
    - [Step 1: Add FaceLok SDK to Gradle project file](#step-1-add-facelok-sdk-to-gradle-project-file)
    - [Step 2: Add play-services-vision to your Gradle project file.](#step-2-add-play-services-vision-to-your-gradle-project-file)
    - [Step 3: Build and run application](#step-3-build-and-run-application)
  - [iOS](#ios)
    - [Step 1: Add FaceLok SDK to your project file](#step-1-add-facelok-sdk-to-your-project-file)
    - [Step 2: Add LibC++ to your project file](#step-2-add-libc-to-your-project-file)
    - [Step 3: Build and run application](#step-3-build-and-run-application-1)
<!-- /TOC -->

## Android

Adding this SDK to your Android project is simple and easy.  Here are the steps that you need to complete:

1. Add **FaceLok.aar** to your project.
2. Add **play-services-vision** to your build.gradle.
3. Build and run application.

### Step 1: Add FaceLok SDK to Gradle project file

The easiest way to add a native SDK to your project is to add it to your **build.gradle** file.

- Choose Gradle Scripts->build.gradle (Module: app) on the left hand project view.

Add the following code in the *dependencies* section:

```
implementation fileTree(dir: '/path/to/facelok-sdk/build-android', include: ['*.aar'])
implementation 'com.google.android.gms:play-services-vision:17.0.2'
```

### Step 2: Add play-services-vision to your Gradle project file.

Note: If you get this error on your app `Didn't find class "com.google.android.gms.vision.face.FaceDetector$Builder" on path` then you need to make sure you add the play-services-vision to your build.gradle.  See above.


### Step 3: Build and run application

Edit MainActivity.java, add the import:
```
import com.ipsidy.faceloksdk.Version;
```

Add the following to the MainActivity class:
```
private Version ver;

static {
	System.loadLibrary("facelok");
}

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);

	ver = Version.create();
	Log.i("faceloktest", ver.versionString());
}
```

At this point, you can build and run the application.  You should see version string from the FaceLok SDK in the output log.

## iOS

Adding this SDK to your Xcode project is simple and easy.  Here are the steps that you need to complete:

1. Add **FaceLok.framework** to your project. 
2. Add **LibC++.framework** to your project.
3. Add **openssl.framework** to your project.
4. Add **poco.framework** to your project.
5. Build and run application.

### Step 1: Add FaceLok SDK to your project file

Next we need to add the framework to the project.  Do the following:

- Right click your project name in the project view on the left, then choose Add Files to...
- Option 1 (my preferred): 
  - Create a symlink to the framework at the root of your test project
    - Need to create symlinks for openssl and poco frameworks as well
  - Click on your project in the left pane, this should pull up the settings in the right.  Make sure you are on the general tab at the top, navigate down to `Frameworks, Libraries, and Embedded Content`.  
    - Click the +
    - Click the dropdown Add Other, then click Add Files
    - Choose your framework symlink, click open.
    - Change Embded & Sign to Do Not Embed in the right side of your newly added framework.
    - Repeat for openssl and poco.
- Option 2: 
  - Navigate to the directory containing the framework (in my case I just left it in my build directory), then choose the `FaceLok.framework` file.  I had it copy the framework to my project with `Copy items if needed` selected.  Make sure you have your target selected so it is added to the target.  Finally I had it on `Create folder references`.  This will need to copy the files or the build will fail.  Makes it hard to update/rebuild the framework.
- Repeat the above for the OpenSSL and Poco frameworks.

### Step 2: Add LibC++ to your project file

Now we need to add C++ lib support to our project:

- Click on the project in the project view on the left.  This should load your project settings in the main view.
- Scroll down to *Linked Frameworks and Libraries*.  You will see our framework is in there.
- Click the + button to add a library
- In the window that comes up search on C++
- Select libc++.tbd and click Add

At this point I built the project just to make sure my framework and c++ linking were working.  It should succeed.

**NOTE**: If the build fails with `Framework not found` type errors, you may need to add a search path to your project.  To do that:

- Click on your project in the left pane
- Click Build Settings tab at the top
- Click All sub tab at the top
- Search on "framework"
- You should see `Frameowrk Search Paths` click this, click + in the resulting diaglog
- Add `$(PROJECT_DIR)` as a path and then set it to search recursively.

Build again and it should work now.

### Step 3: Build and run application

Now make sure to add a request for camera permissions to your info.plist file, like so:
```
<key>NSCameraUsageDescription</key>
<string>${PRODUCT_NAME} needs access to do facial recognition</string>
```

Finally we just need to use the library:

- Click on `ViewController.m` in the project view
- Add import for the version header `#import "FaceLok/FLVersion.h"`
- Add a variable for our Version interface (see code snippet)
- Allocate and get a version string (see code)

Here is how your view code should look:

```
#import "ViewController.h"
#import "FaceLok/FLVersion.h"

@interface ViewController ()

@end

@implementation ViewController {
    FLVersion *verApi;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view, typically from a nib.
    verApi = [FLVersion create];
    NSString *v = [verApi versionString];
    NSLog(v);
}

@end
```

Now build your solution and everything should build and link.  Then run and you should see the FaceLok version info in your log window.

#

[Back to Main Page](../README.md)