@Notify({ context.step == null && currentBuild.result == 'FAILURE' })
void call(context) {
    print("email")
}