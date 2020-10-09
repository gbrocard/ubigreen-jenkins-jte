@Notify({ currentBuild.result == 'FAILURE' })
void call(context) {
    print("slack")
}