package com.example.farmtrade.data.api;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0001\u0006J\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0007"}, d2 = {"Lcom/example/farmtrade/data/api/CatalogApiService;", "", "getCatalogItems", "Lretrofit2/Response;", "Lcom/example/farmtrade/data/db/CatalogResponse;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "RetrofitInstance", "app_debug"})
public abstract interface CatalogApiService {
    
    @retrofit2.http.GET(value = "v3/97e721a7-0a66-4cae-b445-83cc0bcf9010")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getCatalogItems(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.farmtrade.data.db.CatalogResponse>> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\t"}, d2 = {"Lcom/example/farmtrade/data/api/CatalogApiService$RetrofitInstance;", "", "()V", "api", "Lcom/example/farmtrade/data/api/CatalogApiService;", "getApi", "()Lcom/example/farmtrade/data/api/CatalogApiService;", "api$delegate", "Lkotlin/Lazy;", "app_debug"})
    public static final class RetrofitInstance {
        @org.jetbrains.annotations.NotNull
        private static final kotlin.Lazy api$delegate = null;
        @org.jetbrains.annotations.NotNull
        public static final com.example.farmtrade.data.api.CatalogApiService.RetrofitInstance INSTANCE = null;
        
        private RetrofitInstance() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.example.farmtrade.data.api.CatalogApiService getApi() {
            return null;
        }
    }
}