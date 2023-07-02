package com.farmand.foodapp.data.model.home


import com.google.gson.annotations.SerializedName

data class ResponseFoodsList(
    @SerializedName("meals")
    val meals: List<Meal>
) {
    data class Meal(
        @SerializedName("dateModified")
        val dateModified: Any?, // null
        @SerializedName("idMeal")
        val idMeal: String?, // 52941
        @SerializedName("strArea")
        val strArea: String?, // Jamaican
        @SerializedName("strCategory")
        val strCategory: String?, // Beef
        @SerializedName("strCreativeCommonsConfirmed")
        val strCreativeCommonsConfirmed: Any?, // null
        @SerializedName("strDrinkAlternate")
        val strDrinkAlternate: Any?, // null
        @SerializedName("strImageSource")
        val strImageSource: Any?, // null
        @SerializedName("strIngredient1")
        val strIngredient1: String?, // Kidney Beans
        @SerializedName("strIngredient10")
        val strIngredient10: String?, // Beef
        @SerializedName("strIngredient11")
        val strIngredient11: String?, // Water
        @SerializedName("strIngredient12")
        val strIngredient12: String?, // Potatoes
        @SerializedName("strIngredient13")
        val strIngredient13: String?, // Plain Flour
        @SerializedName("strIngredient14")
        val strIngredient14: String?, // Water
        @SerializedName("strIngredient15")
        val strIngredient15: String?, // Coconut Milk
        @SerializedName("strIngredient16")
        val strIngredient16: String?,
        @SerializedName("strIngredient17")
        val strIngredient17: String?,
        @SerializedName("strIngredient18")
        val strIngredient18: String?,
        @SerializedName("strIngredient19")
        val strIngredient19: String?,
        @SerializedName("strIngredient2")
        val strIngredient2: String?, // Carrots
        @SerializedName("strIngredient20")
        val strIngredient20: String?,
        @SerializedName("strIngredient3")
        val strIngredient3: String?, // Spring Onions
        @SerializedName("strIngredient4")
        val strIngredient4: String?, // Thyme
        @SerializedName("strIngredient5")
        val strIngredient5: String?, // Onion
        @SerializedName("strIngredient6")
        val strIngredient6: String?, // Black Pepper
        @SerializedName("strIngredient7")
        val strIngredient7: String?, // Red Pepper
        @SerializedName("strIngredient8")
        val strIngredient8: String?, // Garlic Clove
        @SerializedName("strIngredient9")
        val strIngredient9: String?, // Allspice
        @SerializedName("strInstructions")
        val strInstructions: String?, // Wash and rinse the dried kidney beans.. then cover with water in a deep bowl. Remember as they soak they will expand to at least triple the size they were originally so add a lot of water to the bowl. Soak them overnight or for at least 2 hrs to make the cooking step go quicker. I tossed out the water they were soaked in after it did the job.Have your butcher cut the salted pigtail into 2 inch pieces as it will be very difficult to cut with an ordinary kitchen knife. Wash, then place a deep pot with water and bring to a boil. Cook for 20 minutes, then drain + rinse and repeat (boil again in water). The goal is to make the pieces of pig tails tender and to remove most of the salt it was cured in.Time to start the soup. Place everything in the pot (except the flour and potato), then cover with water and place on a high flame to bring to a boil. As it comes to a boil, skim off any scum/froth at the top and discard. Reduce the heat to a gentle boil and allow it to cook for 1 hr and 15 mins.. basically until the beans are tender and start falling apart.It’s now time to add the potato (and Yams etc if you’re adding it) as well as the coconut milk and continue cooking for 15 minutes.Now is a good time to start making the basic dough for the spinner dumplings. Mix the flour and water (add a pinch of salt if you want) until you have a soft/smooth dough. allow it to rest for 5 minutes, then pinch of a tablespoon at a time and roll between your hands to form a cigarette shape.Add them to the pot, stir well and continue cooking for another 15 minutes on a rolling boil.You’ll notice that I didn’t add any salt to the pot as the remaining salt from the salted pigtails will be enough to properly season this dish. However you can taste and adjust accordingly. Lets recap the timing part of things so you’re not confused. Cook the base of the soup for 1 hr and 15 minute or until tender, then add the potatoes and cook for 15 minutes, then add the dumplings and cook for a further 15 minutes. Keep in mind that this soup will thicken quite a bit as it cools.While this is not a traditional recipe to any one specific island, versions of this soup (sometimes called stewed peas) can be found throughout the Caribbean, Latin America and Africa. A hearty bowl of this soup will surely give you the sleepies (some may call it ethnic fatigue). You can certainly freeze the leftovers and heat it up another day.
        @SerializedName("strMeal")
        val strMeal: String?, // Red Peas Soup
        @SerializedName("strMealThumb")
        val strMealThumb: String?, // https://www.themealdb.com/images/media/meals/sqpqtp1515365614.jpg
        @SerializedName("strMeasure1")
        val strMeasure1: String?, // 2 cups 
        @SerializedName("strMeasure10")
        val strMeasure10: String?, // 2 Lbs
        @SerializedName("strMeasure11")
        val strMeasure11: String?, // 2L
        @SerializedName("strMeasure12")
        val strMeasure12: String?, // 4
        @SerializedName("strMeasure13")
        val strMeasure13: String?, // 1 cup 
        @SerializedName("strMeasure14")
        val strMeasure14: String?, // 1/4 cup
        @SerializedName("strMeasure15")
        val strMeasure15: String?, // 1 cup 
        @SerializedName("strMeasure16")
        val strMeasure16: String?,
        @SerializedName("strMeasure17")
        val strMeasure17: String?,
        @SerializedName("strMeasure18")
        val strMeasure18: String?,
        @SerializedName("strMeasure19")
        val strMeasure19: String?,
        @SerializedName("strMeasure2")
        val strMeasure2: String?, // 1 large
        @SerializedName("strMeasure20")
        val strMeasure20: String?,
        @SerializedName("strMeasure3")
        val strMeasure3: String?, // 2 chopped
        @SerializedName("strMeasure4")
        val strMeasure4: String?, // 4 sprigs
        @SerializedName("strMeasure5")
        val strMeasure5: String?, // 1 Diced
        @SerializedName("strMeasure6")
        val strMeasure6: String?, // 1/2 tsp
        @SerializedName("strMeasure7")
        val strMeasure7: String?, // 2 chopped
        @SerializedName("strMeasure8")
        val strMeasure8: String?, // 4 Mashed
        @SerializedName("strMeasure9")
        val strMeasure9: String?, // 1 tbs
        @SerializedName("strSource")
        val strSource: String?, // http://caribbeanpot.com/caribbean-red-peas-soup/
        @SerializedName("strTags")
        val strTags: String?, // Soup,SideDish
        @SerializedName("strYoutube")
        val strYoutube: String? // https://www.youtube.com/watch?v=1ogCfDXotMw
    )
}