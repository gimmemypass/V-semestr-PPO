package com.example.first

import kotlin.properties.Delegates

class UnitCategory(name: String, baseFirstUnit : String, baseSecondUnit: String, coef: Double) {
    private val _name = name
    private val _firstUnits = mutableMapOf<String, Double>(Pair(baseFirstUnit, 1.0))
    private val _secondUnits = mutableMapOf<String, Double>(Pair(baseSecondUnit, 1.0))
    private val _baseCoef : Double = coef

    public fun addFirstSetUnit(unitName : String, coefToBase : Double){
        _firstUnits[unitName] = coefToBase
    }
    public fun addSecondSetUnit(unitName : String, coefToBase : Double){
        _secondUnits[unitName] = coefToBase
    }

    public fun getCategoryName(): String {
        return _name
    }
    public fun getFirstUnits(): MutableSet<String> {
        return _firstUnits.keys
    }
    public fun getSecondUnits(): MutableSet<String> {
        return _secondUnits.keys
    }
    public fun getFirstBase(unit : String) : Double? {
        return _firstUnits[unit]
    }
    public fun getSecondBase(unit: String) : Double? {
        return _secondUnits[unit]
    }
    public fun getCoef() : Double = _baseCoef

}

class UnitManager(){
    private val _categories = mutableListOf<UnitCategory>()

    public fun getCategories()
    : List<String> {
        return _categories.map{it.getCategoryName()}
    }
    public fun getCategoriesFirstUnits(id : Int): List<String> {
        return _categories[id].getFirstUnits().toList()
    }
    public fun getCategoriesSecondUnits(id : Int): List<String> {
        return _categories[id].getSecondUnits().toList()
    }
    public fun addCategory(category: UnitCategory){
        _categories.add(category)
    }
    public fun translate(value: Double,
                         firstUnit : String,
                         secondUnit : String,
                         catId : Int,
                         mToIn : Boolean)
            : Double {
        val result : Double
        val cat = _categories[catId]
        result = if(mToIn){
            value * cat.getFirstBase(firstUnit)!! * cat.getCoef() / cat.getSecondBase(secondUnit)!!
        } else{
            value * cat.getSecondBase(firstUnit)!! / cat.getCoef() / cat.getFirstBase(secondUnit)!!
        }
        return result
    }
}