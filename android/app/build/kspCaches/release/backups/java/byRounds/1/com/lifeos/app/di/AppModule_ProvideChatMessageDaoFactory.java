package com.lifeos.app.di;

import com.lifeos.app.data.local.LifeOSDatabase;
import com.lifeos.app.data.local.dao.ChatMessageDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class AppModule_ProvideChatMessageDaoFactory implements Factory<ChatMessageDao> {
  private final Provider<LifeOSDatabase> databaseProvider;

  public AppModule_ProvideChatMessageDaoFactory(Provider<LifeOSDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public ChatMessageDao get() {
    return provideChatMessageDao(databaseProvider.get());
  }

  public static AppModule_ProvideChatMessageDaoFactory create(
      Provider<LifeOSDatabase> databaseProvider) {
    return new AppModule_ProvideChatMessageDaoFactory(databaseProvider);
  }

  public static ChatMessageDao provideChatMessageDao(LifeOSDatabase database) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideChatMessageDao(database));
  }
}
