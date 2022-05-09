package com.nirwashh.android.sportclub.model

class Member {
    var id = 0
    var firstName = ""
    var lastName = ""
    var gender = ""
    var sport = ""

    constructor() {}
    constructor(id: Int, firstName: String, lastName: String, gender: String, sport: String) {
        this.id = id
        this.firstName = firstName
        this.lastName = lastName
        this.gender = gender
        this.sport = sport
    }
    constructor(firstName: String, lastName: String, gender: String, sport: String) {
        this.firstName = firstName
        this.lastName = lastName
        this.gender = gender
        this.sport = sport
    }

}