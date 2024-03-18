package com.example.farmtrade.ui.viewmodels;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\fJ\u000e\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0012J\b\u0010\u001e\u001a\u00020\u0019H\u0002J\u000e\u0010\u001f\u001a\u00020\u00192\u0006\u0010 \u001a\u00020\u0015J\u000e\u0010!\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u0012R\u001a\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR \u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\n\"\u0004\b\u000e\u0010\u000fR\u001d\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\nR \u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\n\"\u0004\b\u0017\u0010\u000f\u00a8\u0006\""}, d2 = {"Lcom/example/farmtrade/ui/viewmodels/CatalogViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_catalogItems", "Landroidx/compose/runtime/MutableState;", "", "Lcom/example/farmtrade/data/db/Product;", "_originalCatalogItems", "catalogItems", "getCatalogItems", "()Landroidx/compose/runtime/MutableState;", "currentTag", "Lcom/example/farmtrade/data/db/Tag;", "getCurrentTag", "setCurrentTag", "(Landroidx/compose/runtime/MutableState;)V", "favoriteProducts", "", "", "getFavoriteProducts", "sortOption", "Lcom/example/farmtrade/data/db/SortOption;", "getSortOption", "setSortOption", "filterCatalogItemsByTag", "", "tag", "isProductFavorite", "", "productId", "loadCatalogItems", "sortCatalogItems", "option", "toggleFavorite", "app_debug"})
public final class CatalogViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final androidx.compose.runtime.MutableState<java.util.List<com.example.farmtrade.data.db.Product>> _originalCatalogItems = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.compose.runtime.MutableState<java.util.List<com.example.farmtrade.data.db.Product>> _catalogItems = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.compose.runtime.MutableState<java.util.List<com.example.farmtrade.data.db.Product>> catalogItems = null;
    @org.jetbrains.annotations.NotNull
    private androidx.compose.runtime.MutableState<com.example.farmtrade.data.db.Tag> currentTag;
    @org.jetbrains.annotations.NotNull
    private androidx.compose.runtime.MutableState<com.example.farmtrade.data.db.SortOption> sortOption;
    @org.jetbrains.annotations.NotNull
    private final androidx.compose.runtime.MutableState<java.util.Set<java.lang.String>> favoriteProducts = null;
    
    public CatalogViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.compose.runtime.MutableState<java.util.List<com.example.farmtrade.data.db.Product>> getCatalogItems() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.compose.runtime.MutableState<com.example.farmtrade.data.db.Tag> getCurrentTag() {
        return null;
    }
    
    public final void setCurrentTag(@org.jetbrains.annotations.NotNull
    androidx.compose.runtime.MutableState<com.example.farmtrade.data.db.Tag> p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.compose.runtime.MutableState<com.example.farmtrade.data.db.SortOption> getSortOption() {
        return null;
    }
    
    public final void setSortOption(@org.jetbrains.annotations.NotNull
    androidx.compose.runtime.MutableState<com.example.farmtrade.data.db.SortOption> p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.compose.runtime.MutableState<java.util.Set<java.lang.String>> getFavoriteProducts() {
        return null;
    }
    
    public final void sortCatalogItems(@org.jetbrains.annotations.NotNull
    com.example.farmtrade.data.db.SortOption option) {
    }
    
    public final void filterCatalogItemsByTag(@org.jetbrains.annotations.NotNull
    com.example.farmtrade.data.db.Tag tag) {
    }
    
    public final void toggleFavorite(@org.jetbrains.annotations.NotNull
    java.lang.String productId) {
    }
    
    public final boolean isProductFavorite(@org.jetbrains.annotations.NotNull
    java.lang.String productId) {
        return false;
    }
    
    private final void loadCatalogItems() {
    }
}