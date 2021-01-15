![Ipsidy](../images/ipsidy.png)
# FaceLok Mobile - Supported Platforms

The SDK supports the following platforms and architectures:

| Operating System | Version | Architectures | Unsupported |
| ---------------- | ------- | ------------- | ----------- |
| iOS - Phone |	8.0+ | armv7, armv7s, arm64, arm64e, simulator64 | tvos, watchos, simulator (32-bit) |
| Android | android-21+ | armv7a, x86, x86_6, arm64-v8a |

**Simulator Note:** The majority of the SDK will require a camera. If you have a simulator with a camera you should be able to use the simulator build.

32-bit simulator for iOS is **not** included since any machine newer than 2008 for a mac is 64-bit.

Linux and OSX are able to build desktop versions as well, although a lot of the functionality will be stubbed out since it lacks the hardware required in most cases. Windows will probably work as well since cmake is used to build however it is untested and you would need a posix style compiler like gcc or mingw. It would definitely not work as is with Visual Studio.

## Xamarin

Xamarin libraries are provided for ios and android as well with all the architectures above supported.

#

[Back to Main Page](../README.md)