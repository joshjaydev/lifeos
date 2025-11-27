package com.lifeos.app.ui.screens.values;

import com.lifeos.domain.repository.GoalRepository;
import com.lifeos.domain.repository.PrincipleRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class ValuesViewModel_Factory implements Factory<ValuesViewModel> {
  private final Provider<PrincipleRepository> principleRepositoryProvider;

  private final Provider<GoalRepository> goalRepositoryProvider;

  public ValuesViewModel_Factory(Provider<PrincipleRepository> principleRepositoryProvider,
      Provider<GoalRepository> goalRepositoryProvider) {
    this.principleRepositoryProvider = principleRepositoryProvider;
    this.goalRepositoryProvider = goalRepositoryProvider;
  }

  @Override
  public ValuesViewModel get() {
    return newInstance(principleRepositoryProvider.get(), goalRepositoryProvider.get());
  }

  public static ValuesViewModel_Factory create(
      Provider<PrincipleRepository> principleRepositoryProvider,
      Provider<GoalRepository> goalRepositoryProvider) {
    return new ValuesViewModel_Factory(principleRepositoryProvider, goalRepositoryProvider);
  }

  public static ValuesViewModel newInstance(PrincipleRepository principleRepository,
      GoalRepository goalRepository) {
    return new ValuesViewModel(principleRepository, goalRepository);
  }
}
