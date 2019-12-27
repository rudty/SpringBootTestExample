package org.rudtyz.tests

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Sample(
        @Id
        var name: String = "",
        var number: Int = 0
)