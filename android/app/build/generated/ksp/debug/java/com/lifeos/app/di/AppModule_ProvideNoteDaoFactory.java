package com.lifeos.app.di;

import com.lifeos.app.data.local.LifeOSDatabase;
import com.lifeos.app.data.local.dao.NoteDao;
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
public final class AppModule_ProvideNoteDaoFactory implements Factory<NoteDao> {
  private final Provider<LifeOSDatabase> databaseProvider;

  public AppModule_ProvideNoteDaoFactory(Provider<LifeOSDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public NoteDao get() {
    return provideNoteDao(databaseProvider.get());
  }

  public static AppModule_ProvideNoteDaoFactory create(Provider<LifeOSDatabase> databaseProvider) {
    return new AppModule_ProvideNoteDaoFactory(databaseProvider);
  }

  public static NoteDao provideNoteDao(LifeOSDatabase database) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideNoteDao(database));
  }
}
