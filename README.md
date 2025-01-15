Proje Dökümanına Ulaşmak İçin; 

[Proje Dokümanı](./Soru5_Proje_Dökümanı_Mehmet_Erol.pdf)


Proje Hakkında
Bu proje, bir müşteri ve ürün yönetim sistemi ile birlikte sepet ve sipariş işlemlerini içeren bir e-ticaret uygulamasıdır. Spring Boot kullanılarak geliştirilmiş ve temel e-ticaret işlevselliklerini sağlamayı hedeflemiştir. Proje kapsamında BaseEntity, Cart, CartItem, Customer, Order, PriceHistory ve Product tabloları kullanılmıştır. Ayrıca sepet ve sipariş işlemleri sırasında toplam fiyatın dinamik olarak hesaplanması, ürün stok takibi ve sipariş geçmişi özellikleri de entegre edilmiştir.


Endpointler:

“/customers/add”: (addCustomer()) Yeni bir müşteri ekleyen endpoint. Örnek olarak bir müşteri ekleyelim. Backendim ile entegreli React.js ile bir arayüz tasarladım. 

“/customers/all”: (getAllCustomers()) Tüm müşterileri getiren endpoint
 
“/api/products/{id}”: (getProductById()) Belirli bir product idsine göre ürünü getiren endpoint
 
“/api/products/create”: (createProduct()) Ürün ekleyen endpoint.
 
“/api/products/create”: (updateProduct()) Ürün güncelleme işlemleri için kullanılan endpoint.
 
“/api/products/{id}”: (deleteProduct()) Belirli bir id’ye göre ürün silen endpoint.
 
“/carts/create/{customerId}”: (createCartForCustomer()) Customer için cart oluşturan endpoint. 

“/carts/{cardId}”: (getCartById()) Belirli bir id’deki cartı getiren endpoint. 

“/carts/{id}/empty”: (emptyCart()) Sepeti boşaltan endpoint.
 
"/carts/{cartId}/products/{productId}": (addProductToCart()) Sepete ürün ekleyen endpoint.
 
"carts/{cartId}/products/{productId}": (removeProductFromCart()) Sepetten ürün çıkartan endpont
 
"carts/carts/{cartId}/products/{productId}/increase": (increaseQuantity()) Sepette ürün miktarını arttıran endpoint
 
"carts/carts/{cartId}/products/{productId}/decrease": (decreaseQuantity()) Sepette ürün miktarını azaltan endpoint
 
"/orders/from-cart/{cartId}": (placeOrderFromCart()): Carttaki itemleri siparişini oluşturan endpoint

"/orders/{id}": (getOrderById()) Belirli bir order numarasına göre siparişi getiren endpoint.
 
"/orders/customer/{customerId}": Belirli müşterinin tüm orderlarını getiren endpoint.
 
"/price-history/order/{orderId}": belirli bir order’ın geçmişteki productların fiyatlarını gösteren endpoint.
 
