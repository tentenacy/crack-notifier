package com.tenutz.cracknotifier.util.dummy

data class DummyCracks(
    val id: Int,
    val imageUrl: String,
    val region: String,
    val createdAt: String,
    val accuracy: Float,
)

object Dummies {

    val cracks = listOf(
        DummyCracks(
            1,
            "https://user-images.githubusercontent.com/76826021/166154884-b7683781-9c69-4001-89f5-c6748d6054c1.jpg",
            "정왕동",
            "2022-05-02 12:59:33",
            0.0f,
        ),
        DummyCracks(
            2,
            "https://user-images.githubusercontent.com/76826021/166154916-df65412b-6765-425b-917c-8b9451652534.jpg",
            "정왕1동",
            "2022-05-02 12:59:33",
            99.9998f,
        ),
        DummyCracks(
            3,
            "https://user-images.githubusercontent.com/76826021/166154929-e376ceaa-301e-4b6b-98d2-52070f3bf8de.jpg",
            "정왕2동",
            "2022-05-02 12:59:33",
            100.0f,
        ),
        DummyCracks(
            4,
            "https://user-images.githubusercontent.com/76826021/166154947-f617e0ac-700e-4d6e-9a23-00bbef91367c.jpg",
            "정왕3동",
            "2022-05-02 12:59:33",
            96.5264f,
        ),
        DummyCracks(
            5,
            "https://user-images.githubusercontent.com/76826021/166155041-20de28c5-24de-496b-b397-911964f0650e.jpg",
            "정왕본동",
            "2022-05-02 12:59:33",
            100.0f,
        ),
        DummyCracks(
            6,
            "https://user-images.githubusercontent.com/76826021/166154884-b7683781-9c69-4001-89f5-c6748d6054c1.jpg",
            "정왕동",
            "2022-05-02 12:59:33",
            0.0f,
        ),
        DummyCracks(
            7,
            "https://user-images.githubusercontent.com/76826021/166154916-df65412b-6765-425b-917c-8b9451652534.jpg",
            "정왕1동",
            "2022-05-02 12:59:33",
            99.9998f,
        ),
        DummyCracks(
            8,
            "https://user-images.githubusercontent.com/76826021/166154929-e376ceaa-301e-4b6b-98d2-52070f3bf8de.jpg",
            "정왕2동",
            "2022-05-02 12:59:33",
            100.0f,
        ),
        DummyCracks(
            9,
            "https://user-images.githubusercontent.com/76826021/166154947-f617e0ac-700e-4d6e-9a23-00bbef91367c.jpg",
            "정왕3동",
            "2022-05-02 12:59:33",
            96.5264f,
        ),
        DummyCracks(
            10,
            "https://user-images.githubusercontent.com/76826021/166155041-20de28c5-24de-496b-b397-911964f0650e.jpg",
            "정왕본동",
            "2022-05-02 12:59:33",
            100.0f,
        ),
    )
}