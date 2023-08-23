import java.util.Scanner


data class Archive(
    val name: String,
    val archivesList: MutableList<Note>
)

class ArсhiveActions(
    private val archives: MutableList<Archive> = mutableListOf()
) {
    fun showMenu() {
        println("---АРХИВЫ---")
        println("0. Создать архив")
        println("1. Выход")
        var i: Int = 2
        for (index in archives) {
            println("${i}. ${index.name}")
            i++
        }
    }

    fun selectMenu() {
        val scanner = Scanner(System.`in`)
        var selectedId: Int
        while (true) {
            println("Введите выбранный пункт меню")
            if (scanner.hasNextInt()) {
                selectedId = scanner.nextInt()
                when (selectedId) {
                    0 -> {
                        createArchive()
                        break
                    }
                    1 -> {
                        exitMenu()
                        break

                    }
                    in 2..(archives.size + 1) -> {
                        chooseArchive(selectedId)
                        break
                    }
                    else -> println("Такого пункта меню нет. Пожалуйста, попробуйте еще раз")
                }
            } else {
                println("Вы ввели некорректное значение. Пожалуйста введите цифру ещё раз")
                scanner.next()
            }
        }
    }

    fun createArchive() {
        println("Введите название архива или \"1\" что бы вернуться.")
        val archiveName = readln().trim()
        if (archiveName.isEmpty()) {
            println("Вы не можете создать архив без заголовка")
        } else if (archiveName.equals("1")) {
            return
        } else {
            val notes = mutableListOf<Note>()
            archives.add(Archive(archiveName, notes))
            println("Архив создан")
            return
        }
    }

    private fun chooseArchive(selectedP: Int) {
        val noteOperators = NoteActions(archives[selectedP - 2])
        noteOperators.showMenuNotes()
        noteOperators.selectMenuNote()

    }

    private fun exitMenu() {
        println("Выход из программы")
        System.exit(0)
    }
}

