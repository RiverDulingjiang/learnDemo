var version = '7.0';
// 缓存静态文件
self.addEventListener('install', function (event) {
    event.waitUntil(
        // 缓存指定文件
        caches.open(`version${version}`).then(function (cache) {
            return cache.addAll([
                '/basic/function/serviceWorker.html',
                '/basic/image/serviceWorker.jpg'
            ]);
        })
    );
});

self.addEventListener('activate', function(event) {
    var cacheWhitelist = [`version${version}`];
    event.waitUntil(
        // 遍历 caches 里所有缓存的 keys 值
        caches.keys().then(function(cacheNames) {
            return Promise.all(
                cacheNames.map(function(cacheName) {
                    if (!cacheWhitelist.includes(cacheName)) {
                        return caches.delete(cacheName);
                    }
                })
            );
        })
    );
});

self.addEventListener("fetch", function (e) {
    console.log(e);
    e.respondWith( // 该策略先从网络中获取资源，如果获取失败则再从缓存中读取资源
        fetch(e.request.url)
            .then(function (httpRes) {
                // 请求失败了，直接返回失败的结果
                if (!httpRes || httpRes.status !== 200) {
                    // return httpRes;
                    return caches.match(e.request)
                }
                // 请求成功的话，将请求缓存起来。
                var responseClone = httpRes.clone();
                caches.open(`version${version}`).then(function (cache) {
                    return cache.delete(e.request)
                        .then(function () {
                            cache.put(e.request, responseClone);
                        });
                });
                return httpRes;
            })
            .catch(function (err) { // 无网络情况下从缓存中读取
                console.error(err);
                return caches.match(e.request);
            })
    )
});