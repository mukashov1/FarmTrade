package com.example.farmtrade.ui.screens;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000P\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0007\u001a\u0016\u0010\u0004\u001a\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0007\u001a\u0010\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\bH\u0007\u001a\b\u0010\t\u001a\u00020\u0001H\u0007\u001a$\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u000eH\u0007\u001a*\u0010\u000f\u001a\u00020\u00012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\f0\u00112\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u000eH\u0007\u001a.\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0014\u0010\u0017\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0016\u0012\u0004\u0012\u00020\u00010\u000eH\u0007\u001a:\u0010\u0018\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00142\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00010\u000e2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0007\u001a2\u0010\u001e\u001a\u00020\u00012\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00112\u0006\u0010 \u001a\u00020\u001a2\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00010\u000eH\u0007\u001a\u0014\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\u00112\u0006\u0010$\u001a\u00020%\u00a8\u0006&"}, d2 = {"AddToBasketButton", "", "onAddToBasketClick", "Lkotlin/Function0;", "AddToFavoriteButton", "onAddToFavoriteButton", "CatalogScreen", "navController", "Landroidx/navigation/NavController;", "FilterButton", "ProductCard", "product", "Lcom/example/farmtrade/data/db/ProductItem;", "onProductClicked", "Lkotlin/Function1;", "ProductGridView", "products", "", "SortButton", "showSortMenu", "", "sortOption", "Lcom/example/farmtrade/data/db/SortOption;", "onSortSelected", "TagItem", "currentTag", "Lcom/example/farmtrade/data/db/Tag;", "isSelected", "onSelected", "onDeselected", "TagsCarousel", "tags", "selectedTag", "onTagSelected", "mapProductToImages", "", "productId", "", "app_debug"})
public final class CatalogScreenKt {
    
    @android.annotation.SuppressLint(value = {"StateFlowValueCalledInComposition"})
    @androidx.compose.runtime.Composable
    public static final void CatalogScreen(@org.jetbrains.annotations.NotNull
    androidx.navigation.NavController navController) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void SortButton(boolean showSortMenu, @org.jetbrains.annotations.NotNull
    com.example.farmtrade.data.db.SortOption sortOption, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.example.farmtrade.data.db.SortOption, kotlin.Unit> onSortSelected) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void FilterButton() {
    }
    
    @androidx.compose.runtime.Composable
    public static final void TagItem(@org.jetbrains.annotations.NotNull
    com.example.farmtrade.data.db.Tag currentTag, boolean isSelected, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.example.farmtrade.data.db.Tag, kotlin.Unit> onSelected, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onDeselected) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void TagsCarousel(@org.jetbrains.annotations.NotNull
    java.util.List<? extends com.example.farmtrade.data.db.Tag> tags, @org.jetbrains.annotations.NotNull
    com.example.farmtrade.data.db.Tag selectedTag, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.example.farmtrade.data.db.Tag, kotlin.Unit> onTagSelected) {
    }
    
    @kotlin.OptIn(markerClass = {com.google.accompanist.pager.ExperimentalPagerApi.class})
    @androidx.compose.runtime.Composable
    public static final void ProductGridView(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.farmtrade.data.db.ProductItem> products, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.example.farmtrade.data.db.ProductItem, kotlin.Unit> onProductClicked) {
    }
    
    @com.google.accompanist.pager.ExperimentalPagerApi
    @androidx.compose.runtime.Composable
    public static final void ProductCard(@org.jetbrains.annotations.NotNull
    com.example.farmtrade.data.db.ProductItem product, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.example.farmtrade.data.db.ProductItem, kotlin.Unit> onProductClicked) {
    }
    
    @org.jetbrains.annotations.NotNull
    public static final java.util.List<java.lang.Integer> mapProductToImages(@org.jetbrains.annotations.NotNull
    java.lang.String productId) {
        return null;
    }
    
    @androidx.compose.runtime.Composable
    public static final void AddToFavoriteButton(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onAddToFavoriteButton) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void AddToBasketButton(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onAddToBasketClick) {
    }
}