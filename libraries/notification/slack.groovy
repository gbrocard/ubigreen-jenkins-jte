@Notify({context.step.equals(null) && context.status.equals("FAILURE")})
void call(context) {
    print "slack"
}
