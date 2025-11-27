package com.lifeos.app.di;

import com.lifeos.app.data.local.LifeOSDatabase;
import com.lifeos.app.data.local.dao.FolderDao;
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
public final class AppModule_ProvideFolderDaoFactory implements Factory<FolderDao> {
  private final Provider<LifeOSDatabase> databaseProvider;

  public AppModule_ProvideFolderDaoFactory(Provider<LifeOSDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public FolderDao get() {
    return provideFolderDao(databaseProvider.get());
  }

  public static AppModule_ProvideFolderDaoFactory create(
      Provider<LifeOSDatabase> databaseProvider) {
    return new AppModule_ProvideFolderDaoFactory(databaseProvider);
  }

  public static FolderDao provideFolderDao(LifeOSDatabase database) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideFolderDao(database));
  }
}
