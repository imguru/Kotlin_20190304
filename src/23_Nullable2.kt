package ex23_2

// 안전한 널참조 연산(Safe null access operator) - ?.
class Person(val name: String, val address: Address?)
class Address(val name: String, val postcode: String, val city: City?)
class City(val name: String, val country: Country?)
class Country(val name: String)

// '안전한 널 참조 연산'이 없다면...
fun getCountryName(person: Person): String? {
    var countryName: String? = null
    val address = person.address
    if (address != null) {
        val city = address.city
        if (city != null) {
            val country = city.country
            if (country != null) {
                countryName = country.name
            }
        }
    }

    return countryName
}

fun getCountryName2(person: Person): String? {
    return person.address?.city?.country?.name
}

fun getCountryName3(person: Person): String {
    // Null 인 경우, 기본값을 제공해야 한다.
    //  - 삼항 연산자가 제공되지 않습니다.
    //  ?: - Elvis operator
    //     : null 이면 기본값을 제공하는 연산자
    return person.address?.city?.country?.name ?: "Unknown"
}

fun main() {
    val country = Country("Korea")
    // val city = City("Seoul", country)
    val city = City("Seoul", null)
    val address = Address("Gangnam", "15123", city)
    val person = Person("Tom", address)

    val countryName = getCountryName3(person)
    println(countryName)
}
