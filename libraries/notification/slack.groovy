//only executes at the end of the pipline and if in failure
@Notify({ context.step == null && currentBuild.result == 'FAILURE' })
void call(context) {
    print("slack")
}