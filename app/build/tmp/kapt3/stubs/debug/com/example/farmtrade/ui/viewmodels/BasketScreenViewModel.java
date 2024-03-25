package com.example.farmtrade.ui.viewmodels;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0002J\u000e\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0002J\u0016\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001cR\u001a\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082D\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u000f\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0012\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011\u00a8\u0006\u001d"}, d2 = {"Lcom/example/farmtrade/ui/viewmodels/BasketScreenViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_productsInBasket", "Landroidx/compose/runtime/MutableState;", "", "Lcom/example/farmtrade/data/db/BasketProduct;", "_subtotal", "Landroidx/compose/runtime/MutableDoubleState;", "_total", "productsInBasket", "getProductsInBasket", "()Landroidx/compose/runtime/MutableState;", "shipping", "", "subtotal", "getSubtotal", "()Landroidx/compose/runtime/MutableDoubleState;", "total", "getTotal", "calculateTotals", "", "removeProduct", "productId", "", "sampleProducts", "updateQuantity", "newQuantity", "", "app_debug"})
public final class BasketScreenViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final androidx.compose.runtime.MutableState<java.util.List<com.example.farmtrade.data.db.BasketProduct>> _productsInBasket = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.compose.runtime.MutableState<java.util.List<com.example.farmtrade.data.db.BasketProduct>> productsInBasket = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.compose.runtime.MutableDoubleState _subtotal = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.compose.runtime.MutableDoubleState subtotal = null;
    private final double shipping = 5.0;
    @org.jetbrains.annotations.NotNull
    private final androidx.compose.runtime.MutableDoubleState _total = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.compose.runtime.MutableDoubleState total = null;
    
    public BasketScreenViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.compose.runtime.MutableState<java.util.List<com.example.farmtrade.data.db.BasketProduct>> getProductsInBasket() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.compose.runtime.MutableDoubleState getSubtotal() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.compose.runtime.MutableDoubleState getTotal() {
        return null;
    }
    
    public final void updateQuantity(@org.jetbrains.annotations.NotNull
    java.lang.String productId, int newQuantity) {
    }
    
    public final void removeProduct(@org.jetbrains.annotations.NotNull
    java.lang.String productId) {
    }
    
    private final void calculateTotals() {
    }
    
    private final java.util.List<com.example.farmtrade.data.db.BasketProduct> sampleProducts() {
        return null;
    }
}