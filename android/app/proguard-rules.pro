# Add project specific ProGuard rules here.

# Supabase
-keep class io.github.jan.supabase.** { *; }
-keep class io.ktor.** { *; }

# Kotlin Serialization
-keepattributes *Annotation*, InnerClasses
-dontnote kotlinx.serialization.AnnotationsKt
-keepclassmembers class kotlinx.serialization.json.** { *** Companion; }
-keepclasseswithmembers class kotlinx.serialization.json.** { kotlinx.serialization.KSerializer serializer(...); }
-keep,includedescriptorclasses class com.lifeos.app.**$$serializer { *; }
-keepclassmembers class com.lifeos.app.** { *** Companion; }
-keepclasseswithmembers class com.lifeos.app.** { kotlinx.serialization.KSerializer serializer(...); }
