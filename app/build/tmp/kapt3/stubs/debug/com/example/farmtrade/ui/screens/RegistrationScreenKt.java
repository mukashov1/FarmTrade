package com.example.farmtrade.ui.screens;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u00002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a,\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007\u001a,\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u00032\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007\u001a\u0018\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007\u001a\u001e\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u00072\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00010\u0013H\u0007\u001a,\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u00032\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007\u001a\u000e\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u0003\u00a8\u0006\u0019"}, d2 = {"NameTextField", "", "name", "", "onNameChange", "Lkotlin/Function1;", "isError", "", "PhoneTextField", "phone", "onPhoneChange", "RegistrationScreen", "navController", "Landroidx/navigation/NavController;", "viewModel", "Lcom/example/farmtrade/ui/viewmodels/UserViewModel;", "SignInButton", "isEnabled", "onClick", "Lkotlin/Function0;", "SurnameTextField", "surname", "onSurnameChange", "isValidCyrillic", "input", "app_debug"})
public final class RegistrationScreenKt {
    
    @androidx.compose.runtime.Composable
    public static final void RegistrationScreen(@org.jetbrains.annotations.NotNull
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull
    com.example.farmtrade.ui.viewmodels.UserViewModel viewModel) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable
    public static final void NameTextField(@org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onNameChange, boolean isError) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable
    public static final void SurnameTextField(@org.jetbrains.annotations.NotNull
    java.lang.String surname, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onSurnameChange, boolean isError) {
    }
    
    public static final boolean isValidCyrillic(@org.jetbrains.annotations.NotNull
    java.lang.String input) {
        return false;
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable
    public static final void PhoneTextField(@org.jetbrains.annotations.NotNull
    java.lang.String phone, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onPhoneChange, boolean isError) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void SignInButton(boolean isEnabled, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
}