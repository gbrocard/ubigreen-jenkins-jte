@Notify({ context.currentBuild.equals('FAILURE') })
void call(context) {
    print("slack")
}
