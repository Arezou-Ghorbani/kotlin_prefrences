package arezou.ghorbani.s1_koin.qualifiers

import arezou.ghorbani.s1_koin.QUALIFIER_FAMILY
import arezou.ghorbani.s1_koin.QUALIFIER_NAME
import org.koin.core.qualifier.named
import org.koin.dsl.module

class Human(private val name: String, private val family: String) {
    fun showInfo(): String {
        return "$name $family"
    }
}

fun provideName() = "Mohammad"
fun provideFamily() = "Nouri"

val humanModule = module {
    single(named(QUALIFIER_NAME)) { provideName() }
    factory(named(QUALIFIER_FAMILY)) { provideFamily() }
    factory { Human(get(named(QUALIFIER_NAME)), get(named(QUALIFIER_FAMILY))) }
}