package net.jmp.gradle.plugin.hello

/*
 * (#)HelloPlugin.groovy    0.1.0   02/28/2025
 *
 * @author   Jonathan Parker
 *
 * MIT License
 *
 * Copyright (c) 2025 Jonathan M. Parker
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project

import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.TaskAction

import org.gradle.api.tasks.options.Option

/**
 * The hello task class.
 *
 * This is a task so it is invoked with "gradle hello" or "gradle hello --message 'Greeting'"
 * For help on the task invoke with "gradle help --task hello"
 *
 * @version 0.1.0
 * @since   0.1.0
 */
abstract class HelloTask extends DefaultTask {
    @Input
    @Optional
    String messageText

    @Option(option = "message", description = "The message to print")
    void setMessageText(String message) {
        this.messageText = message
    }

    @TaskAction
    void action() {
        if (this.messageText == null) {
            this.messageText = "Hello"
        }

        println this.messageText
    }
}

class HelloPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        project.tasks.register('hello', HelloTask) {
            group = 'Custom tasks'          // Shows up in "gradle tasks"
            description = 'Prints "Hello"'  // Shows up in "gradle tasks"
        }

        project.gradle.projectsEvaluated {
            println "The groupId is ${project.group}"
            println "The artifactId is ${project.name}"
            println "The version is ${project.version}"
            println "The description is ${project.description}"
            println "The Gradle version is ${project.gradle.gradleVersion}"
        }
    }
}
