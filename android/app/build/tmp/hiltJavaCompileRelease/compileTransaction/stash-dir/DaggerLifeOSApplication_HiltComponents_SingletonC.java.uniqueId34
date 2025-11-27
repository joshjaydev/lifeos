package com.lifeos.app;

import android.app.Activity;
import android.app.Service;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.hilt.work.HiltWorkerFactory;
import androidx.hilt.work.HiltWrapper_WorkerFactoryModule;
import androidx.hilt.work.WorkerAssistedFactory;
import androidx.hilt.work.WorkerFactoryModule_ProvideFactoryFactory;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.work.ListenableWorker;
import com.lifeos.app.data.local.LifeOSDatabase;
import com.lifeos.app.di.AppModule;
import com.lifeos.app.di.AppModule_ProvideActionRepositoryFactory;
import com.lifeos.app.di.AppModule_ProvideAiSuggestionRepositoryFactory;
import com.lifeos.app.di.AppModule_ProvideChatRepositoryFactory;
import com.lifeos.app.di.AppModule_ProvideCompleteActionUseCaseFactory;
import com.lifeos.app.di.AppModule_ProvideCreateActionUseCaseFactory;
import com.lifeos.app.di.AppModule_ProvideCreateFolderUseCaseFactory;
import com.lifeos.app.di.AppModule_ProvideDatabaseFactory;
import com.lifeos.app.di.AppModule_ProvideDeleteActionUseCaseFactory;
import com.lifeos.app.di.AppModule_ProvideDeleteFolderUseCaseFactory;
import com.lifeos.app.di.AppModule_ProvideEventRepositoryFactory;
import com.lifeos.app.di.AppModule_ProvideFolderRepositoryFactory;
import com.lifeos.app.di.AppModule_ProvideGetActionsByFolderUseCaseFactory;
import com.lifeos.app.di.AppModule_ProvideGetActionsUseCaseFactory;
import com.lifeos.app.di.AppModule_ProvideGetFoldersUseCaseFactory;
import com.lifeos.app.di.AppModule_ProvideGoalRepositoryFactory;
import com.lifeos.app.di.AppModule_ProvideLocalDatabaseFactory;
import com.lifeos.app.di.AppModule_ProvideNoteRepositoryFactory;
import com.lifeos.app.di.AppModule_ProvidePrincipleRepositoryFactory;
import com.lifeos.app.di.AppModule_ProvideTimeBlockRepositoryFactory;
import com.lifeos.app.di.AppModule_ProvideUpdateActionUseCaseFactory;
import com.lifeos.app.di.AppModule_ProvideUpdateFolderUseCaseFactory;
import com.lifeos.app.ui.screens.actions.ActionsViewModel;
import com.lifeos.app.ui.screens.actions.ActionsViewModel_HiltModules_KeyModule_ProvideFactory;
import com.lifeos.app.ui.screens.atman.AtmanViewModel;
import com.lifeos.app.ui.screens.atman.AtmanViewModel_HiltModules_KeyModule_ProvideFactory;
import com.lifeos.app.ui.screens.time.TimeViewModel;
import com.lifeos.app.ui.screens.time.TimeViewModel_HiltModules_KeyModule_ProvideFactory;
import com.lifeos.app.ui.screens.values.ValuesViewModel;
import com.lifeos.app.ui.screens.values.ValuesViewModel_HiltModules_KeyModule_ProvideFactory;
import com.lifeos.data.local.LocalDatabase;
import com.lifeos.domain.repository.ActionRepository;
import com.lifeos.domain.repository.AiSuggestionRepository;
import com.lifeos.domain.repository.ChatRepository;
import com.lifeos.domain.repository.EventRepository;
import com.lifeos.domain.repository.FolderRepository;
import com.lifeos.domain.repository.GoalRepository;
import com.lifeos.domain.repository.NoteRepository;
import com.lifeos.domain.repository.PrincipleRepository;
import com.lifeos.domain.repository.TimeBlockRepository;
import com.lifeos.domain.usecase.action.CompleteActionUseCase;
import com.lifeos.domain.usecase.action.CreateActionUseCase;
import com.lifeos.domain.usecase.action.DeleteActionUseCase;
import com.lifeos.domain.usecase.action.GetActionsByFolderUseCase;
import com.lifeos.domain.usecase.action.GetActionsUseCase;
import com.lifeos.domain.usecase.action.UpdateActionUseCase;
import com.lifeos.domain.usecase.folder.CreateFolderUseCase;
import com.lifeos.domain.usecase.folder.DeleteFolderUseCase;
import com.lifeos.domain.usecase.folder.GetFoldersUseCase;
import com.lifeos.domain.usecase.folder.UpdateFolderUseCase;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.flags.HiltWrapper_FragmentGetContextFix_FragmentGetContextFixModule;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.MapBuilder;
import dagger.internal.Preconditions;
import dagger.internal.SetBuilder;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class DaggerLifeOSApplication_HiltComponents_SingletonC {
  private DaggerLifeOSApplication_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    /**
     * @deprecated This module is declared, but an instance is not used in the component. This method is a no-op. For more, see https://dagger.dev/unused-modules.
     */
    @Deprecated
    public Builder appModule(AppModule appModule) {
      Preconditions.checkNotNull(appModule);
      return this;
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    /**
     * @deprecated This module is declared, but an instance is not used in the component. This method is a no-op. For more, see https://dagger.dev/unused-modules.
     */
    @Deprecated
    public Builder hiltWrapper_FragmentGetContextFix_FragmentGetContextFixModule(
        HiltWrapper_FragmentGetContextFix_FragmentGetContextFixModule hiltWrapper_FragmentGetContextFix_FragmentGetContextFixModule) {
      Preconditions.checkNotNull(hiltWrapper_FragmentGetContextFix_FragmentGetContextFixModule);
      return this;
    }

    /**
     * @deprecated This module is declared, but an instance is not used in the component. This method is a no-op. For more, see https://dagger.dev/unused-modules.
     */
    @Deprecated
    public Builder hiltWrapper_WorkerFactoryModule(
        HiltWrapper_WorkerFactoryModule hiltWrapper_WorkerFactoryModule) {
      Preconditions.checkNotNull(hiltWrapper_WorkerFactoryModule);
      return this;
    }

    public LifeOSApplication_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements LifeOSApplication_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public LifeOSApplication_HiltComponents.ActivityRetainedC build() {
      return new ActivityRetainedCImpl(singletonCImpl);
    }
  }

  private static final class ActivityCBuilder implements LifeOSApplication_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public LifeOSApplication_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements LifeOSApplication_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public LifeOSApplication_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements LifeOSApplication_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public LifeOSApplication_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements LifeOSApplication_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public LifeOSApplication_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements LifeOSApplication_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public LifeOSApplication_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements LifeOSApplication_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public LifeOSApplication_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends LifeOSApplication_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    private ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends LifeOSApplication_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    private FragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }
  }

  private static final class ViewCImpl extends LifeOSApplication_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    private ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends LifeOSApplication_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    private ActivityCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public void injectMainActivity(MainActivity mainActivity) {
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Set<String> getViewModelKeys() {
      return SetBuilder.<String>newSetBuilder(4).add(ActionsViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(AtmanViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(TimeViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(ValuesViewModel_HiltModules_KeyModule_ProvideFactory.provide()).build();
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }
  }

  private static final class ViewModelCImpl extends LifeOSApplication_HiltComponents.ViewModelC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    private Provider<ActionsViewModel> actionsViewModelProvider;

    private Provider<AtmanViewModel> atmanViewModelProvider;

    private Provider<TimeViewModel> timeViewModelProvider;

    private Provider<ValuesViewModel> valuesViewModelProvider;

    private ViewModelCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam,
        ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;

      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.actionsViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.atmanViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.timeViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
      this.valuesViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 3);
    }

    @Override
    public Map<String, Provider<ViewModel>> getHiltViewModelMap() {
      return MapBuilder.<String, Provider<ViewModel>>newMapBuilder(4).put("com.lifeos.app.ui.screens.actions.ActionsViewModel", ((Provider) actionsViewModelProvider)).put("com.lifeos.app.ui.screens.atman.AtmanViewModel", ((Provider) atmanViewModelProvider)).put("com.lifeos.app.ui.screens.time.TimeViewModel", ((Provider) timeViewModelProvider)).put("com.lifeos.app.ui.screens.values.ValuesViewModel", ((Provider) valuesViewModelProvider)).build();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.lifeos.app.ui.screens.actions.ActionsViewModel 
          return (T) new ActionsViewModel(singletonCImpl.getActionsUseCase(), singletonCImpl.getActionsByFolderUseCase(), singletonCImpl.createActionUseCase(), singletonCImpl.updateActionUseCase(), singletonCImpl.deleteActionUseCase(), singletonCImpl.completeActionUseCase(), singletonCImpl.getFoldersUseCase(), singletonCImpl.createFolderUseCase(), singletonCImpl.updateFolderUseCase(), singletonCImpl.deleteFolderUseCase());

          case 1: // com.lifeos.app.ui.screens.atman.AtmanViewModel 
          return (T) new AtmanViewModel(singletonCImpl.provideChatRepositoryProvider.get(), singletonCImpl.provideAiSuggestionRepositoryProvider.get(), singletonCImpl.provideActionRepositoryProvider.get(), singletonCImpl.provideEventRepositoryProvider.get(), singletonCImpl.provideNoteRepositoryProvider.get(), singletonCImpl.provideGoalRepositoryProvider.get(), singletonCImpl.providePrincipleRepositoryProvider.get(), singletonCImpl.provideTimeBlockRepositoryProvider.get());

          case 2: // com.lifeos.app.ui.screens.time.TimeViewModel 
          return (T) new TimeViewModel(singletonCImpl.getActionsUseCase(), singletonCImpl.getFoldersUseCase(), singletonCImpl.completeActionUseCase(), singletonCImpl.provideTimeBlockRepositoryProvider.get(), singletonCImpl.provideEventRepositoryProvider.get());

          case 3: // com.lifeos.app.ui.screens.values.ValuesViewModel 
          return (T) new ValuesViewModel(singletonCImpl.providePrincipleRepositoryProvider.get(), singletonCImpl.provideGoalRepositoryProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends LifeOSApplication_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    private Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    private ActivityRetainedCImpl(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;

      initialize();

    }

    @SuppressWarnings("unchecked")
    private void initialize() {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle 
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends LifeOSApplication_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    private ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }
  }

  private static final class SingletonCImpl extends LifeOSApplication_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    private Provider<LifeOSDatabase> provideDatabaseProvider;

    private Provider<LocalDatabase> provideLocalDatabaseProvider;

    private Provider<ActionRepository> provideActionRepositoryProvider;

    private Provider<FolderRepository> provideFolderRepositoryProvider;

    private Provider<ChatRepository> provideChatRepositoryProvider;

    private Provider<AiSuggestionRepository> provideAiSuggestionRepositoryProvider;

    private Provider<EventRepository> provideEventRepositoryProvider;

    private Provider<NoteRepository> provideNoteRepositoryProvider;

    private Provider<GoalRepository> provideGoalRepositoryProvider;

    private Provider<PrincipleRepository> providePrincipleRepositoryProvider;

    private Provider<TimeBlockRepository> provideTimeBlockRepositoryProvider;

    private SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;
      initialize(applicationContextModuleParam);

    }

    private HiltWorkerFactory hiltWorkerFactory() {
      return WorkerFactoryModule_ProvideFactoryFactory.provideFactory(Collections.<String, Provider<WorkerAssistedFactory<? extends ListenableWorker>>>emptyMap());
    }

    private GetActionsUseCase getActionsUseCase() {
      return AppModule_ProvideGetActionsUseCaseFactory.provideGetActionsUseCase(provideActionRepositoryProvider.get());
    }

    private GetActionsByFolderUseCase getActionsByFolderUseCase() {
      return AppModule_ProvideGetActionsByFolderUseCaseFactory.provideGetActionsByFolderUseCase(provideActionRepositoryProvider.get());
    }

    private CreateActionUseCase createActionUseCase() {
      return AppModule_ProvideCreateActionUseCaseFactory.provideCreateActionUseCase(provideActionRepositoryProvider.get());
    }

    private UpdateActionUseCase updateActionUseCase() {
      return AppModule_ProvideUpdateActionUseCaseFactory.provideUpdateActionUseCase(provideActionRepositoryProvider.get());
    }

    private DeleteActionUseCase deleteActionUseCase() {
      return AppModule_ProvideDeleteActionUseCaseFactory.provideDeleteActionUseCase(provideActionRepositoryProvider.get());
    }

    private CompleteActionUseCase completeActionUseCase() {
      return AppModule_ProvideCompleteActionUseCaseFactory.provideCompleteActionUseCase(provideActionRepositoryProvider.get());
    }

    private GetFoldersUseCase getFoldersUseCase() {
      return AppModule_ProvideGetFoldersUseCaseFactory.provideGetFoldersUseCase(provideFolderRepositoryProvider.get());
    }

    private CreateFolderUseCase createFolderUseCase() {
      return AppModule_ProvideCreateFolderUseCaseFactory.provideCreateFolderUseCase(provideFolderRepositoryProvider.get());
    }

    private UpdateFolderUseCase updateFolderUseCase() {
      return AppModule_ProvideUpdateFolderUseCaseFactory.provideUpdateFolderUseCase(provideFolderRepositoryProvider.get());
    }

    private DeleteFolderUseCase deleteFolderUseCase() {
      return AppModule_ProvideDeleteFolderUseCaseFactory.provideDeleteFolderUseCase(provideFolderRepositoryProvider.get());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam) {
      this.provideDatabaseProvider = DoubleCheck.provider(new SwitchingProvider<LifeOSDatabase>(singletonCImpl, 2));
      this.provideLocalDatabaseProvider = DoubleCheck.provider(new SwitchingProvider<LocalDatabase>(singletonCImpl, 1));
      this.provideActionRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<ActionRepository>(singletonCImpl, 0));
      this.provideFolderRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<FolderRepository>(singletonCImpl, 3));
      this.provideChatRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<ChatRepository>(singletonCImpl, 4));
      this.provideAiSuggestionRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<AiSuggestionRepository>(singletonCImpl, 5));
      this.provideEventRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<EventRepository>(singletonCImpl, 6));
      this.provideNoteRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<NoteRepository>(singletonCImpl, 7));
      this.provideGoalRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<GoalRepository>(singletonCImpl, 8));
      this.providePrincipleRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<PrincipleRepository>(singletonCImpl, 9));
      this.provideTimeBlockRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<TimeBlockRepository>(singletonCImpl, 10));
    }

    @Override
    public void injectLifeOSApplication(LifeOSApplication lifeOSApplication) {
      injectLifeOSApplication2(lifeOSApplication);
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return Collections.<Boolean>emptySet();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    private LifeOSApplication injectLifeOSApplication2(LifeOSApplication instance) {
      LifeOSApplication_MembersInjector.injectWorkerFactory(instance, hiltWorkerFactory());
      return instance;
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.lifeos.domain.repository.ActionRepository 
          return (T) AppModule_ProvideActionRepositoryFactory.provideActionRepository(singletonCImpl.provideLocalDatabaseProvider.get());

          case 1: // com.lifeos.data.local.LocalDatabase 
          return (T) AppModule_ProvideLocalDatabaseFactory.provideLocalDatabase(singletonCImpl.provideDatabaseProvider.get());

          case 2: // com.lifeos.app.data.local.LifeOSDatabase 
          return (T) AppModule_ProvideDatabaseFactory.provideDatabase(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 3: // com.lifeos.domain.repository.FolderRepository 
          return (T) AppModule_ProvideFolderRepositoryFactory.provideFolderRepository(singletonCImpl.provideLocalDatabaseProvider.get());

          case 4: // com.lifeos.domain.repository.ChatRepository 
          return (T) AppModule_ProvideChatRepositoryFactory.provideChatRepository(singletonCImpl.provideLocalDatabaseProvider.get());

          case 5: // com.lifeos.domain.repository.AiSuggestionRepository 
          return (T) AppModule_ProvideAiSuggestionRepositoryFactory.provideAiSuggestionRepository(singletonCImpl.provideLocalDatabaseProvider.get());

          case 6: // com.lifeos.domain.repository.EventRepository 
          return (T) AppModule_ProvideEventRepositoryFactory.provideEventRepository(singletonCImpl.provideLocalDatabaseProvider.get());

          case 7: // com.lifeos.domain.repository.NoteRepository 
          return (T) AppModule_ProvideNoteRepositoryFactory.provideNoteRepository(singletonCImpl.provideLocalDatabaseProvider.get());

          case 8: // com.lifeos.domain.repository.GoalRepository 
          return (T) AppModule_ProvideGoalRepositoryFactory.provideGoalRepository(singletonCImpl.provideLocalDatabaseProvider.get());

          case 9: // com.lifeos.domain.repository.PrincipleRepository 
          return (T) AppModule_ProvidePrincipleRepositoryFactory.providePrincipleRepository(singletonCImpl.provideLocalDatabaseProvider.get());

          case 10: // com.lifeos.domain.repository.TimeBlockRepository 
          return (T) AppModule_ProvideTimeBlockRepositoryFactory.provideTimeBlockRepository(singletonCImpl.provideLocalDatabaseProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
