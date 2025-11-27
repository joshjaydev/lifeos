package com.lifeos.app;

import androidx.hilt.work.HiltWorkerFactory;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class LifeOSApplication_MembersInjector implements MembersInjector<LifeOSApplication> {
  private final Provider<HiltWorkerFactory> workerFactoryProvider;

  public LifeOSApplication_MembersInjector(Provider<HiltWorkerFactory> workerFactoryProvider) {
    this.workerFactoryProvider = workerFactoryProvider;
  }

  public static MembersInjector<LifeOSApplication> create(
      Provider<HiltWorkerFactory> workerFactoryProvider) {
    return new LifeOSApplication_MembersInjector(workerFactoryProvider);
  }

  @Override
  public void injectMembers(LifeOSApplication instance) {
    injectWorkerFactory(instance, workerFactoryProvider.get());
  }

  @InjectedFieldSignature("com.lifeos.app.LifeOSApplication.workerFactory")
  public static void injectWorkerFactory(LifeOSApplication instance,
      HiltWorkerFactory workerFactory) {
    instance.workerFactory = workerFactory;
  }
}
