package com.example.farmtrade.ui.screens;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0005\u000f\u0010\u0011\u0012\u0013B*\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\b\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\tR\u001c\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\b\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\r\u0082\u0001\u0005\u0014\u0015\u0016\u0017\u0018\u00a8\u0006\u0019"}, d2 = {"Lcom/example/farmtrade/ui/screens/Screen;", "", "route", "", "icon", "Lkotlin/Function0;", "Landroidx/compose/ui/graphics/painter/Painter;", "Landroidx/compose/runtime/Composable;", "title", "(Ljava/lang/String;Lkotlin/jvm/functions/Function0;Ljava/lang/String;)V", "getIcon", "()Lkotlin/jvm/functions/Function0;", "getRoute", "()Ljava/lang/String;", "getTitle", "Cart", "Catalog", "Home", "Offers", "Profile", "Lcom/example/farmtrade/ui/screens/Screen$Cart;", "Lcom/example/farmtrade/ui/screens/Screen$Catalog;", "Lcom/example/farmtrade/ui/screens/Screen$Home;", "Lcom/example/farmtrade/ui/screens/Screen$Offers;", "Lcom/example/farmtrade/ui/screens/Screen$Profile;", "app_debug"})
public abstract class Screen {
    @org.jetbrains.annotations.NotNull
    private final java.lang.String route = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.jvm.functions.Function0<androidx.compose.ui.graphics.painter.Painter> icon = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String title = null;
    
    private Screen(java.lang.String route, kotlin.jvm.functions.Function0<? extends androidx.compose.ui.graphics.painter.Painter> icon, java.lang.String title) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getRoute() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlin.jvm.functions.Function0<androidx.compose.ui.graphics.painter.Painter> getIcon() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getTitle() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/example/farmtrade/ui/screens/Screen$Cart;", "Lcom/example/farmtrade/ui/screens/Screen;", "()V", "app_debug"})
    public static final class Cart extends com.example.farmtrade.ui.screens.Screen {
        @org.jetbrains.annotations.NotNull
        public static final com.example.farmtrade.ui.screens.Screen.Cart INSTANCE = null;
        
        private Cart() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/example/farmtrade/ui/screens/Screen$Catalog;", "Lcom/example/farmtrade/ui/screens/Screen;", "()V", "app_debug"})
    public static final class Catalog extends com.example.farmtrade.ui.screens.Screen {
        @org.jetbrains.annotations.NotNull
        public static final com.example.farmtrade.ui.screens.Screen.Catalog INSTANCE = null;
        
        private Catalog() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/example/farmtrade/ui/screens/Screen$Home;", "Lcom/example/farmtrade/ui/screens/Screen;", "()V", "app_debug"})
    public static final class Home extends com.example.farmtrade.ui.screens.Screen {
        @org.jetbrains.annotations.NotNull
        public static final com.example.farmtrade.ui.screens.Screen.Home INSTANCE = null;
        
        private Home() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/example/farmtrade/ui/screens/Screen$Offers;", "Lcom/example/farmtrade/ui/screens/Screen;", "()V", "app_debug"})
    public static final class Offers extends com.example.farmtrade.ui.screens.Screen {
        @org.jetbrains.annotations.NotNull
        public static final com.example.farmtrade.ui.screens.Screen.Offers INSTANCE = null;
        
        private Offers() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/example/farmtrade/ui/screens/Screen$Profile;", "Lcom/example/farmtrade/ui/screens/Screen;", "()V", "app_debug"})
    public static final class Profile extends com.example.farmtrade.ui.screens.Screen {
        @org.jetbrains.annotations.NotNull
        public static final com.example.farmtrade.ui.screens.Screen.Profile INSTANCE = null;
        
        private Profile() {
        }
    }
}