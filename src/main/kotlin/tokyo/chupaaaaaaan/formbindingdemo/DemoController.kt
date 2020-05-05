package tokyo.chupaaaaaaan.formbindingdemo

import org.springframework.beans.propertyeditors.StringTrimmerEditor
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import tokyo.chupaaaaaaan.formbindingdemo.form.DemoForm


@Controller
@RequestMapping("/")
class DemoController {
    val selectItem: Map<String, String> = mapOf(
            "select_A" to "A",
            "select_B" to "B",
            "select_C" to "C",
            "select_D" to "D",
            "select_E" to "E")

    val checkItem: Map<String, String> = mapOf(
            "check_A" to "A",
            "check_B" to "B",
            "check_C" to "C",
            "check_D" to "D",
            "check_E" to "E")

    val radioItem: Map<String, String> = mapOf(
            "radio_A" to "A",
            "radio_B" to "B",
            "radio_C" to "C",
            "radio_D" to "D",
            "radio_E" to "E")

    // フォームオブジェクトに、空文字でなくnullをバインドする。
    @InitBinder
    fun emptyStringToNull(binder: WebDataBinder) {
        // bind empty strings as null
        binder.registerCustomEditor(String::class.java, StringTrimmerEditor(true))
    }

    @GetMapping("view")
    fun formPage(model: Model,
                 @ModelAttribute demoForm: DemoForm): String {

        model.addAttribute("selectItem", selectItem)
        model.addAttribute("checkItem", checkItem)
        model.addAttribute("radioItem", radioItem)

        val key = BindingResult.MODEL_KEY_PREFIX + "demoForm"
        if(model.asMap().containsKey("error")) {
            model.addAttribute(key, model.getAttribute("error"))
        }

        return "view"
    }

    @PostMapping("confirm")
    fun postForm(model: Model,
                 redirectAttributes: RedirectAttributes,
                 @ModelAttribute @Validated demoForm: DemoForm,
                 result: BindingResult): String {

        if (result.hasErrors()) {
            println(result)
            model.addAttribute("demoForm", demoForm)
            redirectAttributes.addFlashAttribute("error", result)
            return "redirect:/view"
        }

        // 今回は直接Modelを返しているが、ドメイン知識を画面と結合させないためにViewに変換して渡してもよい。
        // その際はViewにmapperを実装する。
        model.addAttribute("demoModel", demoForm.mapToModel())
        return "confirm"
    }


}