import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class LayoutView extends StatefulWidget {
  const LayoutView({Key? key}) : super(key: key);

  @override
  State<LayoutView> createState() => _LayoutViewState();
}

class _LayoutViewState extends State<LayoutView> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(title: const Text('LayoutView App'),),
        body: SafeArea(child: Column(
          children: <Widget>[
            Row(

            ),
            Text(
              ""
            ),
            SizedBox(

            ),
            //Expanded(

            //)
          ],
        ),)
    );
  }
}
