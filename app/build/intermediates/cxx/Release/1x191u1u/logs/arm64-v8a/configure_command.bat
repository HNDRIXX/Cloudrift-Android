@echo off
"C:\\Users\\PC\\AppData\\Local\\Android\\Sdk\\ndk\\23.2.8568313\\ndk-build.cmd" ^
  "NDK_PROJECT_PATH=null" ^
  "APP_BUILD_SCRIPT=C:\\Users\\PC\\Desktop\\cloudrift\\app\\src\\main\\jni\\Android.mk" ^
  "NDK_APPLICATION_MK=C:\\Users\\PC\\Desktop\\cloudrift\\app\\src\\main\\jni\\Application.mk" ^
  "APP_ABI=arm64-v8a" ^
  "NDK_ALL_ABIS=arm64-v8a" ^
  "NDK_DEBUG=0" ^
  "APP_PLATFORM=android-16" ^
  "NDK_OUT=C:\\Users\\PC\\Desktop\\cloudrift\\app\\build\\intermediates\\cxx\\Release\\1x191u1u/obj" ^
  "NDK_LIBS_OUT=C:\\Users\\PC\\Desktop\\cloudrift\\app\\build\\intermediates\\cxx\\Release\\1x191u1u/lib" ^
  "PRODUCT_FLAVOR=nonRoot" ^
  "APP_SHORT_COMMANDS=false" ^
  "LOCAL_SHORT_COMMANDS=false" ^
  -B ^
  -n