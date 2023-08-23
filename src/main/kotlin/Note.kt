import java.util.*

data class Note(
    val article: String,
    var text: String
)

class NoteActions(
    private val archive: Archive
) {
    private val archiveName = archive.name

    fun showMenuNotes() {
        println("---Архив: ${archiveName}---")
        println("0. Создать заметку")
        println("1. Назад")

        val noteList = archive.archivesList
        if (noteList.isEmpty()) {
            println("В архиве ${archiveName} заметок нет")
        } else {

            var i: Int = 2
            for (index in noteList) {
                println("${i}. ${index.article}")
                i++
            }
        }
    }

    fun selectMenuNote() {
        val scanner = Scanner(System.`in`)
        val noteList = archive.archivesList
        var selectedId: Int
        while (true) {
            println("Введите выбранный пункт меню")
            if (scanner.hasNextInt()) {
                selectedId = scanner.nextInt()
                when (selectedId) {
                    0 -> {
                        createNote()
                        break
                    }
                    1 -> {
                        return
                    }
                    in 2..(noteList.size + 1) -> {
                        showNote(noteList[selectedId - 2])
                        break
                    }

                    else -> println("Вы ввели некорректное значение. Пожалуйста введите цифру ещё раз")
                }
            } else {
                println("Вы ввели некорректное значение. Пожалуйста введите цифру ещё раз")
                scanner.next()
            }
        }
    }

    fun showNote(note: Note) {

        println("${note.article} \n  ${note.text} \n")
        println("--------")
        println("Введите '1', чтобы вернуться в предыдущее меню")

        do {
            val selectedId = readln().toIntOrNull()
            when (selectedId) {
                1 -> return
                null -> println("Вы ввели недопустимый символ")
                else -> {
                    println("Неверный выбор.")
                }
            }
        } while (true)
    }


    fun createNote() {
        println("Введите имя новой заметки. Или \"1\" что бы вернуться.")
        val noteName = readln().trim()
        if (noteName.isEmpty()) {
            println("Вы не можете создать заметку без заголовка")
        } else if (noteName.equals("1")) {
            return
        } else {
            print("Введите текст заметки: ")
            val noteText = readln().trim()
            val note = Note(noteName, noteText)
            archive.archivesList.add(note)
            println("Заметка создана")
            return
        }
    }

}

