package com.example.farmtrade.ui.viewmodels;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u001d\u001a\u00020\u001eH\u0002J\u000e\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0016J\u000e\u0010\"\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020\u0016R\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00070\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001d\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00150\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013R \u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0013\"\u0004\b\u001b\u0010\u001c\u00a8\u0006#"}, d2 = {"Lcom/example/farmtrade/ui/viewmodels/CatalogViewModel;", "Landroidx/lifecycle/ViewModel;", "catalogDataStoreRepository", "Lcom/example/farmtrade/data/repository/DataStoreRepository;", "(Lcom/example/farmtrade/data/repository/DataStoreRepository;)V", "_catalogItems", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/example/farmtrade/data/db/ProductItem;", "_originalCatalogItems", "Landroidx/compose/runtime/MutableState;", "Lcom/example/farmtrade/data/db/Product;", "catalogItems", "Lkotlinx/coroutines/flow/StateFlow;", "getCatalogItems", "()Lkotlinx/coroutines/flow/StateFlow;", "currentTag", "Lcom/example/farmtrade/data/db/Tag;", "getCurrentTag", "()Landroidx/compose/runtime/MutableState;", "favoriteProducts", "", "", "getFavoriteProducts", "sortOption", "Lcom/example/farmtrade/data/db/SortOption;", "getSortOption", "setSortOption", "(Landroidx/compose/runtime/MutableState;)V", "fetchCatalogItemsFromFirestore", "", "isProductFavorite", "", "productId", "toggleFavorite", "app_debug"})
public final class CatalogViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final com.example.farmtrade.data.repository.DataStoreRepository catalogDataStoreRepository = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.compose.runtime.MutableState<java.util.List<com.example.farmtrade.data.db.Product>> _originalCatalogItems = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.compose.runtime.MutableState<com.example.farmtrade.data.db.Tag> currentTag = null;
    @org.jetbrains.annotations.NotNull
    private androidx.compose.runtime.MutableState<com.example.farmtrade.data.db.SortOption> sortOption;
    @org.jetbrains.annotations.NotNull
    private final androidx.compose.runtime.MutableState<java.util.Set<java.lang.String>> favoriteProducts = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.example.farmtrade.data.db.ProductItem>> _catalogItems = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.farmtrade.data.db.ProductItem>> catalogItems = null;
    
    public CatalogViewModel(@org.jetbrains.annotations.NotNull
    com.example.farmtrade.data.repository.DataStoreRepository catalogDataStoreRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.compose.runtime.MutableState<com.example.farmtrade.data.db.Tag> getCurrentTag() {
        return null;
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
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.farmtrade.data.db.ProductItem>> getCatalogItems() {
        return null;
    }
    
    private final void fetchCatalogItemsFromFirestore() {
    }
    
    public final void toggleFavorite(@org.jetbrains.annotations.NotNull
    java.lang.String productId) {
    }
    
    public final boolean isProductFavorite(@org.jetbrains.annotations.NotNull
    java.lang.String productId) {
        return false;
    }
}