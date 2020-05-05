package tokyo.chupaaaaaaan.formbindingdemo.form

import org.springframework.format.annotation.DateTimeFormat
import tokyo.chupaaaaaaan.formbindingdemo.model.DemoModel
import java.util.*
import javax.validation.constraints.*

data class DemoForm(

        // Controller側の@InitBinderで、未入力（空白トリム結果が空文字）のときはnullをバインドする。
        // そのため、NotEmpty等ではなくNotNullを使用する。
        // 未入力をnullでなく空文字と扱っていい場合もあるので、要件を確認。

        @field:NotNull
        @field:Size(min=2, max=20)
        var stringSimple: String?,

        @field:Size(min=2, max=20)
        var stringNullable: String?,

        // 日付をStringとしてほしい場合。
        // パターンが日付として妥当かを正規表現のみで判定するのは難しいので、別途相関精査でチェックする。
        @field:NotNull
        @field:Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}")
        var stringDate: String?,

        // java.util.Date型の値が欲しい場合。
        // 入力がpatternにマッチするときだけ、Date型に変換後バインドされる。
        // java.time.*で定義されるLocalDate等にバインディングしたい場合は、
        // thymeleafで表示するために、 org.thymeleaf.extras:thymeleaf-extras-java8time への依存を追加する。
        @field:NotNull
        @field:DateTimeFormat(pattern = "yyyy-MM-dd")
        var date: Date?,

        @field:NotNull
        @field:Email
        var stringEmail: String?,

        var textArea: String?,

        @field:NotNull
        var selectSingle: String?,

        // 複数選択可能なプルダウンで、Listが空でないことを確認したい場合
        @field:NotEmpty
        var selectMultiple: List<String>?,

        // チェックボックスで、チェックの有無を判定する場合
        @field:NotNull
        var checkSingle: Boolean?,

        // 空リストを許容する場合はNotNullを設定する
        @field:NotNull
        var checkMultiple: List<String>?,

        // ラジオボタンの要素など、いずれかの要素が一つだけ存在する場合
        @field:NotNull
        var radio: String?
) {
        fun mapToModel(): DemoModel {
                return DemoModel(
                        stringSimple!!,
                        stringNullable?:"",
                        stringDate!!,
                        date!!,
                        stringEmail!!,
                        textArea?:"",
                        selectSingle!!,
                        selectMultiple!!,
                        checkSingle!!,
                        checkMultiple!!,
                        radio!!
                )
        }
}
