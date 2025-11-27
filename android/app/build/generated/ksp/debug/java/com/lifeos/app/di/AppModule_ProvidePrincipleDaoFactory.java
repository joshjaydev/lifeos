package com.lifeos.app.di;

import com.lifeos.app.data.local.LifeOSDatabase;
import com.lifeos.app.data.local.dao.PrincipleDao;
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
public final class AppModule_ProvidePrincipleDaoFactory implements Factory<PrincipleDao> {
  private final Provider<LifeOSDatabase> databaseProvider;

  public AppModule_ProvidePrincipleDaoFactory(Provider<LifeOSDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public PrincipleDao get() {
    return providePrincipleDao(databaseProvider.get());
  }

  public static AppModule_ProvidePrincipleDaoFactory create(
      Provider<LifeOSDatabase> databaseProvider) {
    return new AppModule_ProvidePrincipleDaoFactory(databaseProvider);
  }

  public static PrincipleDao providePrincipleDao(LifeOSDatabase database) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.providePrincipleDao(database));
  }
}
