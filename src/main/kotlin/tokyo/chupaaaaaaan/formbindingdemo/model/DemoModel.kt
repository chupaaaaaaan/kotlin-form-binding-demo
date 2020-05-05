package tokyo.chupaaaaaaan.formbindingdemo.model

import java.util.*

data class DemoModel(
        val stringSimple: String,
        val stringNullable: String,
        val stringDate: String,
        val date: Date,
        val stringEmail: String,
        val textArea: String,
        val selectSingle: String,
        val selectMultiple: List<String>,
        val checkSingle: Boolean,
        val checkMultiple: List<String>,
        val radio: String
)
