package com.example.farmtrade.ui.screens;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000.\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\u001a2\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\bH\u0007\u001a\b\u0010\t\u001a\u00020\u0001H\u0007\u001a\u0016\u0010\n\u001a\u00020\u00012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00010\bH\u0007\u001a \u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000eH\u0007\u001a\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00030\u0012\u00a8\u0006\u0013"}, d2 = {"BasketItem", "", "product", "Lcom/example/farmtrade/data/db/BasketProduct;", "onQuantityChanged", "Lkotlin/Function1;", "", "onDeleteClicked", "Lkotlin/Function0;", "BasketScreen", "CheckoutButton", "onCheckout", "OrderSummary", "subtotal", "", "shipping", "total", "sampleProducts", "", "app_debug"})
public final class BasketScreenKt {
    
    @androidx.compose.runtime.Composable
    public static final void BasketScreen() {
    }
    
    @kotlin.OptIn(markerClass = {com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi.class})
    @androidx.compose.runtime.Composable
    public static final void BasketItem(@org.jetbrains.annotations.NotNull
    com.example.farmtrade.data.db.BasketProduct product, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onQuantityChanged, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onDeleteClicked) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void OrderSummary(double subtotal, double shipping, double total) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void CheckoutButton(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onCheckout) {
    }
    
    @org.jetbrains.annotations.NotNull
    public static final java.util.List<com.example.farmtrade.data.db.BasketProduct> sampleProducts() {
        return null;
    }
}