package com.lifeos.app.di;

import com.lifeos.data.local.LocalDatabase;
import com.lifeos.domain.repository.ChatRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class AppModule_ProvideChatRepositoryFactory implements Factory<ChatRepository> {
  private final Provider<LocalDatabase> localDatabaseProvider;

  public AppModule_ProvideChatRepositoryFactory(Provider<LocalDatabase> localDatabaseProvider) {
    this.localDatabaseProvider = localDatabaseProvider;
  }

  @Override
  public ChatRepository get() {
    return provideChatRepository(localDatabaseProvider.get());
  }

  public static AppModule_ProvideChatRepositoryFactory create(
      Provider<LocalDatabase> localDatabaseProvider) {
    return new AppModule_ProvideChatRepositoryFactory(localDatabaseProvider);
  }

  public static ChatRepository provideChatRepository(LocalDatabase localDatabase) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideChatRepository(localDatabase));
  }
}
